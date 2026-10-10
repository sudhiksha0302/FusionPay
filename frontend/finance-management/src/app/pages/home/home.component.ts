import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { FooterComponent } from '../../shared/footer/footer.component';

import { ProductService } from '../../core/services/product.service';
import { Product } from '../../core/models/product';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  products: Product[] = [];

  loading = true;

  error = '';

  constructor(private productService: ProductService) {}

  ngOnInit(): void {

    this.loadProducts();

  }

  loadProducts(): void {

    this.productService.getAllProducts().subscribe({

      next: (response) => {

        this.products = response.data;

        this.loading = false;

      },

      error: (err) => {

        console.log(err);

        this.loading = false;

        this.error = 'Unable to load products.';

      }

    });

  }

}