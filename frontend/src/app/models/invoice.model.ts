export interface Invoice {
  id: number;
  invoiceName: string;
  receivedDate: Date;
  dueDate: Date;
  paid: string;
  value: string;
}
