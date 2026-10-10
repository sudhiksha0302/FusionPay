import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../core/services/admin.service';

@Component({
  selector: 'app-manage-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  users: any[] = [];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.adminService.getAllUsers().subscribe({
      next: (response: any[]) => {
        this.users = response;
      },
      error: (error) => {
        console.error('Error fetching users', error);
      }
    });
  }

}