import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createProduct } from "../../services/productService";

const AddProduct = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [quantityInStock, setQuantityInStock] = useState("");
  const [image, setImage] = useState(null);  // Armazena o arquivo da imagem
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);  // Para exibir mensagem de sucesso

  // Função para capturar a imagem escolhida e validar
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file && file.type.startsWith("image/")) {
      setImage(file);
      setError(null);  // Remove qualquer mensagem de erro existente
    } else {
      setError("Por favor, selecione um arquivo de imagem válido.");
      setImage(null);
    }
  };

  // Função para adicionar um produto
  const onSubmit = async (e) => {
    e.preventDefault();

    // Verificar se os campos obrigatórios estão preenchidos
    if (!name || !price || !quantityInStock) {
      setError("Preencha todos os campos obrigatórios.");
      return;
    }

    // Criar o objeto FormData para enviar o arquivo e os outros dados
    const formData = new FormData();
    formData.append("name", name);
    formData.append("description", description);
    formData.append("price", price);
    formData.append("quantityInStock", quantityInStock);
    if (image) {
      formData.append("image", image);  // Adiciona a imagem ao FormData
    }

    try {
      // Faz a requisição POST para o backend com FormData
      await createProduct(formData);
      setSuccess(true);  // Exibe mensagem de sucesso
      navigate("/");  // Redireciona para a página principal após o sucesso
    } catch (err) {
      // Tratamento de erros detalhado
      if (err.response && err.response.status === 400) {
        setError("Erro de validação! Verifique os dados fornecidos.");
      } else if (err.response && err.response.status === 500) {
        setError("Erro no servidor! Tente novamente mais tarde.");
      } else {
        setError("Erro ao adicionar o produto.");
      }
    }
  };

  return (
      <div className="container">
        <div className="section">
          <div className="card">
            <div className="card-header">
              <p className="card-header-title">Adicionar Produto</p>
            </div>
            <div className="card-content">
              {error && <p className="has-text-danger">{error}</p>}
              {success && <p className="has-text-success">Produto adicionado com sucesso!</p>}
              <form onSubmit={onSubmit}>
                <div className="columns">
                  <div className="column is-3">
                    <input
                        className="input"
                        type="text"
                        placeholder="Nome do Produto"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                  </div>
                  <div className="column is-3">
                  <textarea
                      className="textarea"
                      placeholder="Descrição do Produto"
                      value={description}
                      onChange={(e) => setDescription(e.target.value)}
                  />
                  </div>
                  <div className="column is-3">
                    <input
                        className="input"
                        type="number"
                        placeholder="Preço do Produto"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                        required
                    />
                  </div>
                  <div className="column is-3">
                    <input
                        className="input"
                        type="number"
                        placeholder="Quantidade em Estoque"
                        value={quantityInStock}
                        onChange={(e) => setQuantityInStock(e.target.value)}
                        required
                    />
                  </div>
                  <div className="column is-3">
                    <input
                        className="input"
                        type="file"
                        accept="image/*"
                        onChange={handleImageChange}
                    />
                  </div>
                </div>
                <button className="button is-primary">Adicionar Produto</button>
              </form>
            </div>
          </div>
        </div>
      </div>
  );
};

export default AddProduct;
