import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createProduct } from "../../services/productService";
import { Form, Button, Card, Alert, Container, Row, Col } from "react-bootstrap";

const AddProduct = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [quantityInStock, setQuantityInStock] = useState("");
  const [image, setImage] = useState(null); // Armazena o arquivo da imagem
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false); // Para exibir mensagem de sucesso

  // Função para capturar a imagem escolhida e validar
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file && file.type.startsWith("image/")) {
      setImage(file);
      setError(null); // Remove qualquer mensagem de erro existente
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
      formData.append("image", image); // Adiciona a imagem ao FormData
    }

    try {
      // Faz a requisição POST para o backend com FormData
      await createProduct(formData);
      setSuccess(true); // Exibe mensagem de sucesso
      navigate("/home"); // Redireciona para a página principal após o sucesso
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
      <Container>
        <Row className="justify-content-center mt-5">
          <Col md={8}>
            <Card className="shadow-sm">
              <Card.Header as="h4">Adicionar Produto</Card.Header>
              <Card.Body>
                {error && <Alert variant="danger">{error}</Alert>}
                {success && <Alert variant="success">Produto adicionado com sucesso!</Alert>}
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
                    Adicionar Produto
                  </Button>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
  );
};

export default AddProduct;
