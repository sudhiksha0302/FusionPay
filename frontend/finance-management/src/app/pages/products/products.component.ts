import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router} from '@angular/router';

import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { FooterComponent } from '../../shared/footer/footer.component';

import { ProductService } from '../../core/services/product.service';
import { Product } from '../../core/models/product';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,NavbarComponent,FooterComponent],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];

  search = '';

  loading = true;

  constructor(
    private service: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {

    this.service.getAllProducts().subscribe({

      next: (res) => {

        this.products = res.data;

        this.loading = false;

      }

    });

  }

  searchProduct() {

    if(this.search.trim()==""){

      this.loadProducts();

      return;

    }

    this.service.searchProducts(this.search).subscribe({

      next:(res)=>{

        this.products=res.data;

      }

    })

  }

  buy(product:Product){

    this.router.navigate(['/purchase',product.productId]);

  }

}