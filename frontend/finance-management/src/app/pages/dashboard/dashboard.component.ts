import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { FooterComponent } from '../../shared/footer/footer.component';

import { AuthService } from '../../core/services/auth.service';
import { User } from '../../core/models/user';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  user!: User;

  loading = true;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {

    const id = this.authService.getUserId();

    this.authService.getUserById(id).subscribe({

      next: (data) => {

        this.user = data;

        this.loading = false;

      },

      error: (err) => {

        console.log(err);

        this.loading = false;

      }

    });

  }

}