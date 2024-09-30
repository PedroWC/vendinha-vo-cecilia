import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {createProduct} from "../../services/productService";

const AddProduct = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");  // Novo campo
  const [price, setPrice] = useState("");
  const [quantityInStock, setQuantityInStock] = useState("");  // Novo campo
  const [picture, setPicture] = useState("");  // Campo mantido
  const [error, setError] = useState(null);

  // Função para adicionar um produto
  const onSubmit = async (e) => {
    e.preventDefault();
    const newProduct = {
      name,
      description,  // Inclui a descrição no objeto de criação
      price,
      quantityInStock,  // Inclui a quantidade no objeto de criação
      picture,  // Mantém o campo picture
    };

    try {
      // Faz a requisição POST para o backend
      await createProduct(newProduct);
      navigate("/");  // Redireciona para a página principal após o sucesso
    } catch (err) {
      setError("Erro ao adicionar o produto");
    }
  };

  return (
      <div className="container">
        <div className="section">
          <div className="card">
            <div className="card-header">
              <p className="card-header-title">Add A Product</p>
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
                        placeholder="Enter Quantity In Stock"
                        value={quantityInStock}
                        onChange={(e) => setQuantityInStock(e.target.value)}
                        required
                    />
                  </div>
                  <div className="column is-3">
                    <input
                        className="input"
                        type="text"
                        placeholder="Product Picture URL"
                        value={picture}
                        onChange={(e) => setPicture(e.target.value)}
                    />
                  </div>
                </div>
                <button className="button is-primary">Add Product</button>
              </form>
            </div>
          </div>
        </div>
      </div>
  );
};

export default AddProduct;
