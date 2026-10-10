export interface Transaction {

  purchaseId: number;

  userId: number;

  productId: number;

  productName?: string;

  totalAmount: number;

  emiDuration: number;

  emiAmount: number;

  purchaseDate: string;

  status: string;

}