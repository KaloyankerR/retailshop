export interface SaleItem {
  saleItemId: number;
  saleId: number;
  productId: number;
  quantity: number;
  unitPrice: number;
  totalPrice: number;
  productName?: string;
}
