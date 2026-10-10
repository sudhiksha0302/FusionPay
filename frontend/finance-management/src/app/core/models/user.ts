export interface User {

  userId?: number;

  name: string;

  dateOfBirth: string;

  email: string;

  phoneNumber: string;

  username: string;

  password: string;

  address: string;

  bankName: string;

  accountNumber: string;

  ifscCode: string;

  cardType: string;

  verified?: boolean;

  activated?: boolean;

}