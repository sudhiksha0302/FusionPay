import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { FooterComponent } from '../../shared/footer/footer.component';

import { ProductService } from '../../core/services/product.service';
import { PurchaseService } from '../../core/services/purchase.service';
import { AuthService } from '../../core/services/auth.service';

import { Product } from '../../core/models/product';

@Component({
  selector: 'app-purchase',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './purchase.component.html',
  styleUrl: './purchase.component.css'
})
export class PurchaseComponent implements OnInit {

  product!: Product;

  emiDuration = 6;

  emiAmount = 0;

  loading = true;

  successMessage = '';

  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private purchaseService: PurchaseService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.productService.getProductById(id).subscribe({

      next: (res) => {

        this.product = res.data;

        this.calculateEMI();

        this.loading = false;

      }

    });

  }

  calculateEMI() {

    this.emiAmount = this.product.price / this.emiDuration;

  }

  confirmPurchase() {

  const request = {
    userId: this.authService.getUserId(),
    productId: this.product.productId,
    emiDuration: this.emiDuration
  };

  console.log("Sending Request:", request);

this.purchaseService.purchase(request).subscribe({

  next: (res: string) => {

    this.successMessage = res;

    setTimeout(() => {

      this.router.navigate(['/dashboard']);

    }, 1500);

  },

  error: (err) => {

    this.errorMessage = "Purchase Failed";

    console.log(err);

  }

});

}

}