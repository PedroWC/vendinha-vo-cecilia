import React, { useState, useEffect, useCallback } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getProductById, updateProduct } from "../../services/productService";
import { Form, Button, Card, Container, Row, Col, Alert } from "react-bootstrap";

const EditProduct = () => {
  let { id } = useParams();
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [quantityInStock, setQuantityInStock] = useState("");
  const [image, setImage] = useState(null); // Para armazenar o arquivo de imagem
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false); // Para exibir mensagem de sucesso

  // Função para carregar os detalhes do produto do backend
  const loadProduct = useCallback(async () => {
    try {
      const product = await getProductById(id);
      setName(product.name);
      setDescription(product.description);
      setPrice(product.price);
      setQuantityInStock(product.quantityInStock);
      setImage(null); // Não exibimos a imagem existente, deixamos o campo para upload
      setSuccess(false); // Reseta a mensagem de sucesso após carregar o produto
    } catch (error) {
      setError("Erro ao carregar o produto");
    }
  }, [id]);

  useEffect(() => {
    loadProduct();
  }, [loadProduct]);

  // Função para capturar a imagem escolhida e validar
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file && file.type.startsWith("image/")) {
      setImage(file); // Armazena o arquivo de imagem
      setError(null); // Remove qualquer erro existente
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
      formData.append("image", image); // Adiciona a imagem ao FormData, se houver
    }

    try {
      await updateProduct(id, formData); // Usa a função updateProduct para atualizar o produto com FormData
      setSuccess(true); // Exibe mensagem de sucesso
      navigate("/home"); // Redireciona para a página principal após a atualização
    } catch (error) {
      setError("Erro ao atualizar o produto");
    }
  };

  return (
      <Container className="mt-5">
        <Row className="justify-content-center">
          <Col md={8}>
            <Card className="shadow-sm">
              <Card.Header as="h4">Editar Produto</Card.Header>
              <Card.Body>
                {error && <Alert variant="danger">{error}</Alert>}
                {success && <Alert variant="success">Produto atualizado com sucesso!</Alert>}
                <Form onSubmit={onSubmit}>
                  <Row>
                    <Col md={6}>
                      <Form.Group controlId="productName" className="mb-3">
                        <Form.Label>Nome do Produto</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Nome do Produto"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                      </Form.Group>
                    </Col>
                    <Col md={6}>
                      <Form.Group controlId="productPrice" className="mb-3">
                        <Form.Label>Preço do Produto</Form.Label>
                        <Form.Control
                            type="number"
                            placeholder="Preço do Produto"
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                            required
                        />
                      </Form.Group>
                    </Col>
                    <Col md={6}>
                      <Form.Group controlId="productDescription" className="mb-3">
                        <Form.Label>Descrição do Produto</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={3}
                            placeholder="Descrição do Produto"
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        />
                      </Form.Group>
                    </Col>
                    <Col md={6}>
                      <Form.Group controlId="productStock" className="mb-3">
                        <Form.Label>Quantidade em Estoque</Form.Label>
                        <Form.Control
                            type="number"
                            placeholder="Quantidade em Estoque"
                            value={quantityInStock}
                            onChange={(e) => setQuantityInStock(e.target.value)}
                            required
                        />
                      </Form.Group>
                    </Col>
                    <Col md={12}>
                      <Form.Group controlId="productImage" className="mb-3">
                        <Form.Label>Imagem do Produto</Form.Label>
                        <Form.Control type="file" accept="image/*" onChange={handleImageChange} />
                      </Form.Group>
                    </Col>
                  </Row>
                  <Button variant="primary" type="submit">
                    Atualizar Produto
                  </Button>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
  );
};

export default EditProduct;
