import React, { useState, useEffect, useCallback } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getProductById, updateProduct } from "../../services/productService";  // Importa as funções de serviço

const EditProduct = () => {
  let { id } = useParams();
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [quantityInStock, setQuantityInStock] = useState("");
  const [image, setImage] = useState(null);  // Para armazenar o arquivo de imagem
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);  // Para exibir mensagem de sucesso

  // Função para carregar os detalhes do produto do backend
  const loadProduct = useCallback(async () => {
    try {
      const product = await getProductById(id);
      setName(product.name);
      setDescription(product.description);
      setPrice(product.price);
      setQuantityInStock(product.quantityInStock);
      setImage(null);  // Não exibimos a imagem existente, deixamos o campo para upload
      setSuccess(false);  // Reseta a mensagem de sucesso após carregar o produto
    } catch (error) {
      setError("Erro ao carregar o produto");
    }
  }, [id]);

  useEffect(() => {
    loadProduct().then(() => {});
  }, [loadProduct]);

  // Função para capturar a imagem escolhida e validar
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file && file.type.startsWith("image/")) {
      setImage(file);  // Armazena o arquivo de imagem
      setError(null);  // Remove qualquer erro existente
    } else {
      setError("Por favor, selecione um arquivo de imagem válido.");
      setImage(null);
    }
  };

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
      formData.append("image", image);  // Adiciona a imagem ao FormData, se houver
    }

    try {
      await updateProduct(id, formData);  // Usa a função updateProduct para atualizar o produto com FormData
      setSuccess(true);  // Exibe mensagem de sucesso
      navigate("/home");  // Redireciona para a página principal após a atualização
    } catch (error) {
      setError("Erro ao atualizar o produto");
    }
  };

  return (
      <div className="container">
        <div className="section">
          <div className="card">
            <div className="card-header">
              <p className="card-header-title">Editar Produto</p>
            </div>
            <div className="card-content">
              {error && <p className="has-text-danger">{error}</p>}
              {success && <p className="has-text-success">Produto atualizado com sucesso!</p>}
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
                <button className="button is-primary">Atualizar Produto</button>
              </form>
            </div>
          </div>
        </div>
      </div>
  );
};

export default EditProduct;
