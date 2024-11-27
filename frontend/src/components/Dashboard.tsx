import React from "react";
import CustomerList from "./CustomerList";
import ProductList from "./ProductList";
import ShopList from "./ShopList";
import InventoryList from "./InventoryList";
import SaleList from "./SaleList";
import { Tab } from "@headlessui/react";

const Dashboard: React.FC = () => {
  return (
    <div className="w-full max-w-7xl mx-auto">
      <h1 className="text-3xl font-bold mb-8">Dashboard</h1>
      <Tab.Group>
        <Tab.List className="flex space-x-1 rounded-xl bg-blue-900/20 p-1">
          {["Customers", "Products", "Shops", "Inventory", "Sales"].map(
            (category) => (
              <Tab
                key={category}
                className={({ selected }) =>
                  `w-full py-2.5 text-sm leading-5 font-medium text-blue-700 rounded-lg ${
                    selected
                      ? "bg-white shadow"
                      : "text-blue-100 hover:bg-white/[0.12] hover:text-white"
                  }`
                }
              >
                {category}
              </Tab>
            )
          )}
        </Tab.List>
        <Tab.Panels className="mt-4">
          <Tab.Panel>
            <CustomerList />
          </Tab.Panel>
          <Tab.Panel>
            <ProductList />
          </Tab.Panel>
          <Tab.Panel>
            <ShopList />
          </Tab.Panel>
          <Tab.Panel>
            <InventoryList />
          </Tab.Panel>
          <Tab.Panel>
            <SaleList />
          </Tab.Panel>
        </Tab.Panels>
      </Tab.Group>
    </div>
  );
};

export default Dashboard;
