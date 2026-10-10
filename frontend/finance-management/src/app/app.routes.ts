import { Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProductsComponent } from './pages/products/products.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { TransactionsComponent } from './pages/transactions/transactions.component';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { PurchaseComponent } from './pages/purchase/purchase.component';

import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { ManageUsersComponent } from './pages/manage-users/manage-users.component';
import { ActivateUsersComponent } from './pages/activate-users/activate-users.component';
import { ManageProductsComponent } from './pages/manage-products/manage-products.component';

import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [

  // ================= HOME =================

  {
    path: '',
    component: HomeComponent
  },

  // ================= AUTH =================

  {
    path: 'login',
    component: LoginComponent
  },

  {
    path: 'register',
    component: RegisterComponent
  },

  {
    path: 'forgot-password',
    component: ForgotPasswordComponent
  },

  // ================= USER =================

  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard]
  },

  {
    path: 'products',
    component: ProductsComponent,
    canActivate: [authGuard]
  },

  {
    path: 'product/:id',
    component: ProductDetailsComponent,
    canActivate: [authGuard]
  },

  {
    path: 'purchase/:id',
    component: PurchaseComponent,
    canActivate: [authGuard]
  },

  {
    path: 'payment',
    component: PaymentComponent,
    canActivate: [authGuard]
  },

  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [authGuard]
  },

  {
    path: 'transactions',
    component: TransactionsComponent,
    canActivate: [authGuard]
  },

  // ================= ADMIN =================

  {
    path: 'admin',
    component: AdminDashboardComponent
  },

  {
    path: 'admin/manage-users',
    component: ManageUsersComponent
  },

  {
    path: 'admin/activate-users',
    component: ActivateUsersComponent
  },

  {
    path: 'admin/manage-products',
    component: ManageProductsComponent
  },

  // ================= 404 =================

  {
    path: '**',
    loadComponent: () =>
      import('./shared/not-found/not-found.component')
        .then(m => m.NotFoundComponent)
  }

];