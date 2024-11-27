import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import CustomerForm from "./components/CustomerForm";
import CustomerSales from "./components/CustomerSales";
import ProductForm from "./components/ProductForm";
import ShopForm from "./components/ShopForm";
import InventoryForm from "./components/InventoryForm";
import SaleForm from "./components/SaleForm";
import Dashboard from "./components/Dashboard";
import ErrorBoundary from "./config/ErrorBoundary";

const App: React.FC = () => {
  return (
    <ErrorBoundary>
      <Router>
        <div className="container mx-auto mt-4">
          <Routes>
            <Route path="/" element={<Dashboard />} />

            {/* Customer Routes */}
            <Route path="/add" element={<CustomerForm />} />
            <Route path="/edit/:id" element={<CustomerForm />} />
            <Route
              path="/customers/:customerId/sales"
              element={<CustomerSales />}
            />

            {/* Product Routes */}
            <Route path="/products/add" element={<ProductForm />} />
            <Route path="/products/edit/:id" element={<ProductForm />} />

            {/* Shop Routes */}
            <Route path="/shops/add" element={<ShopForm />} />
            <Route path="/shops/edit/:id" element={<ShopForm />} />

            {/* Inventory Routes */}
            <Route path="/inventory/add" element={<InventoryForm />} />
            <Route path="/inventory/edit/:id" element={<InventoryForm />} />

            {/* Sales Routes */}
            <Route path="/sales/add" element={<SaleForm />} />
          </Routes>
        </div>
      </Router>
    </ErrorBoundary>
  );
};

export default App;
