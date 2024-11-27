import { SaleItem } from "./SaleItem";

export interface Sale {
  saleId: number;
  customerId: number;
  shopId: number;
  saleDate: string;
  totalAmount: number;
  items: SaleItem[];
}
