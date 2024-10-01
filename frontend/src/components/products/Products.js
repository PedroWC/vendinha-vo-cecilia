import React from "react";
import ProductItem from "./ProductItem";
import View from "../layout/View";

const Products = ({ products, setProducts, view }) => {
  if (products.length === 0) {
    return <p>Nenhum produto encontrado.</p>;
  }

  return (
      <div className="container">
        <div className="row">
          {products.map((product) => (
              <View key={product.id} view={view}>
                <ProductItem
                    product={product}
                    onDelete={(id) => setProducts(products.filter((p) => p.id !== id))}
                />
              </View>
          ))}
        </div>
      </div>
  );
};

export default Products;
