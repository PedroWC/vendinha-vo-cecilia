import React, { useEffect, useState } from "react";
import ProductItem from "./ProductItem";
import View from "../layout/View";
import { getAllProducts } from '../../services/productService';

const Products = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [view] = useState("column");  // Estado local para controlar o layout

  // Função para buscar os produtos do backend
  const fetchProducts = async () => {
    try {
      const productsData = await getAllProducts();  // Usa a função de serviço para buscar os produtos
      setProducts(productsData);  // Armazena os produtos no estado
    } catch (err) {
      setError('Erro ao carregar produtos');
    } finally {
      setLoading(false);  // Fim do carregamento
    }
  };

  useEffect(() => {
    fetchProducts().then(() => {});
  }, []);

  if (loading) {
    return <p>Carregando produtos...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
      <div className="container">
        <div className="columns is-multiline">
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
