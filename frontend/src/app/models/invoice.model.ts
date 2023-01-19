import { User } from "./user.model";

export interface Invoice {
  id: number;
  invoiceName: string;
  createdAt: Date;
  dueDate: Date;
  payed: boolean;
  providerName: string;
  price:number;
  user:User;
  userName:string;
  userEmail:string;
}
