import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getProductById, updateProduct } from "../../services/productService";  // Importa as funções de serviço

const EditProduct = () => {
  let { id } = useParams();
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [quantityInStock, setQuantityInStock] = useState("");
  const [picture, setPicture] = useState("");
  const [error, setError] = useState(null);

  // Função para carregar os detalhes do produto do backend
  const loadProduct = async () => {
    try {
      const product = await getProductById(id);  // Usa a função getProductById para buscar o produto
      setName(product.name);
      setDescription(product.description);
      setPrice(product.price);
      setQuantityInStock(product.quantityInStock);
      setPicture(product.picture);
    } catch (error) {
      setError('Erro ao carregar produto');
    }
  };

  useEffect(() => {
    loadProduct().then(() => {});
  }, [id]);

  const onSubmit = async (e) => {
    e.preventDefault();

    // Verificar se os campos obrigatórios estão preenchidos
    if (!name || !price || !quantityInStock) {
      setError('Preencha todos os campos obrigatórios.');
      return;
    }

    const updatedProduct = {
      name,
      description: description || "",  // Descrição opcional
      price,
      quantityInStock,
      picture,
    };

    try {
      await updateProduct(id, updatedProduct);  // Usa a função updateProduct para atualizar o produto
      navigate("/home");  // Redireciona para a página principal após a atualização
    } catch (error) {
      setError('Erro ao atualizar produto');
    }
  };

  return (
      <div className="container">
        <div className="section">
          <div className="card">
            <div className="card-header">
              <p className="card-header-title">Edit A Product</p>
            </div>
            <div className="card-content">
              {error && <p className="has-text-danger">{error}</p>}
              <form onSubmit={onSubmit}>
                <div className="columns">
                  <div className="column is-3">
                    <input
                        className="input"
                        type="text"
                        placeholder="Enter Product Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                  </div>
                  <div className="column is-3">
                  <textarea
                      className="textarea"
                      placeholder="Enter Product Description"
                      value={description}
                      onChange={(e) => setDescription(e.target.value)}
                  />
                  </div>
                  <div className="column is-3">
                    <input
                        className="input"
                        type="number"
                        placeholder="Enter Product Price"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                        required
                    />
                  </div>
                  <div className="column is-3">
                    <input
                        className="input"
                        type="number"
                        placeholder="Enter Product Quantity In Stock"
                        value={quantityInStock}
                        onChange={(e) => setQuantityInStock(e.target.value)}
                        required
                    />
                  </div>
                  <div className="column is-3">
                    <input
                        className="input"
                        type="text"
                        placeholder="Enter Product Picture URL"
                        value={picture}
                        onChange={(e) => setPicture(e.target.value)}
                    />
                  </div>
                </div>
                <button className="button is-primary">Update Product</button>
              </form>
            </div>
          </div>
        </div>
      </div>
  );
};

export default EditProduct;
