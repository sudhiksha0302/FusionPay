import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminService } from '../../core/services/admin.service';

@Component({
  selector: 'app-activate-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './activate-users.component.html',
  styleUrls: ['./activate-users.component.css']
})
export class ActivateUsersComponent implements OnInit {

  users: any[] = [];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {

    this.adminService.getAllUsers().subscribe({

      next: (data) => {

        // Show only users who are NOT activated
        this.users = data.filter(user => !user.activated);

      },

      error: (err) => {
        console.error(err);
      }

    });

  }

  activateUser(id: number) {

    this.adminService.activateUser(id).subscribe({

      next: () => {

        alert("User Activated Successfully");

        this.loadUsers();

      },

      error: (err) => {

        console.error(err);

        alert("Unable to activate user");

      }

    });

  }

}