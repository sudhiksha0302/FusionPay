import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../core/services/admin.service';

@Component({
  selector: 'app-manage-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './manage-products.component.html',
  styleUrls: ['./manage-products.component.css']
})
export class ManageProductsComponent implements OnInit {

  products:any[]=[];

  constructor(private adminService:AdminService){}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(){

    this.adminService.getAllProducts().subscribe({

      next:(data)=>{
        this.products=data;
      },

      error:(err)=>{
        console.log(err);
      }

    });

  }

}