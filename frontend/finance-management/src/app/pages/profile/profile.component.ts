import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { FooterComponent } from '../../shared/footer/footer.component';

import { AuthService } from '../../core/services/auth.service';

import { User } from '../../core/models/user';
import { Bank } from '../../core/models/bank';
import { Card } from '../../core/models/card';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {

  user!: User;

  bank!: Bank;

  card!: Card;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {

    const id = this.authService.getUserId();

    this.authService.getUserById(id)
      .subscribe(res => this.user = res);

    this.authService.getBankDetails(id)
      .subscribe(res => this.bank = res);

    this.authService.getCardDetails(id).subscribe({

  next: (res) => {

    console.log("FULL RESPONSE =", res);
    console.log("cardType =", res.cardType);
    console.log("cardNumber =", res.cardNumber);

    this.card = res;

  },

  error: (err) => {

    console.log(err);

  }

});

  }

}