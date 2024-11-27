import { Sale } from "./Sale";

export interface Customer {
  customerId: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber?: string;
  address?: string;
  sales: Sale[];
}
