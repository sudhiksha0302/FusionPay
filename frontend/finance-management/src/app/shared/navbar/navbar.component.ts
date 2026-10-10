import { Component, DoCheck } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements DoCheck {

  isLoggedIn = false;
  username = '';

  constructor(private router: Router) {}

  ngDoCheck(): void {

    this.isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    this.username = localStorage.getItem('name') || '';

  }

  logout() {

    localStorage.clear();

    this.router.navigate(['/']);

  }

}