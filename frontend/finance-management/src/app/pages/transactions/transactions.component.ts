import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { FooterComponent } from '../../shared/footer/footer.component';

import { TransactionService } from '../../core/services/transaction.service';
import { ProductService } from '../../core/services/product.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.css'
})
export class TransactionsComponent implements OnInit {

  transactions: any[] = [];

  constructor(
    private transactionService: TransactionService,
    private productService: ProductService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {

    const userId = this.authService.getUserId();

    this.transactionService.getAllTransactions().subscribe({

      next: (res: any[]) => {

        this.transactions = res.filter(t => t.userId === userId);

        this.transactions.forEach(transaction => {

          this.productService.getProductById(transaction.productId).subscribe({

            next: (productRes: any) => {

              // If your Product API returns ProductResponse directly
              transaction.productName =
                productRes.productName ?? productRes.data?.productName;

            },

            error: () => {

              transaction.productName = 'Unknown Product';

            }

          });

        });

      }

    });

  }

}