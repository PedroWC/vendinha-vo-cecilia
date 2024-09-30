import React from "react";
import { Link } from "react-router-dom";
import { deleteProduct } from "../../services/productService";
import { Card, Button } from "react-bootstrap";

const ProductItem = ({ product, onDelete }) => {
    // Função para deletar o produto via API
    const handleDeleteProduct = async (id) => {
        try {
            await deleteProduct(id);  // Chama a função de exclusão do serviço
            onDelete(id);  // Notifica o componente pai para remover o produto da lista
        } catch (error) {
            console.error("Erro ao deletar o produto:", error);
        }
    };

    return (
        <Card ClassName="h-100 shadow-sm">
        <div className="box box__custom">
            <Link
                to={`/editProduct/${product.id}`}
                className="button button-edit is-warning"
            >
                Edit
            </Link>
            <Button
                onClick={() => handleDeleteProduct(product.id)}
                className="button button-delete is-danger"
            >
                Delete
            </Button>
            <h1 className="title is-1">
                {product.imageBase64 && (
                    <div className="d-flex justify-content-center mt-3">
                        {/* Wrapper flexível para centralizar a imagem */}
                        <img
                            src={`data:image/jpeg;base64,${product.imageBase64}`}
                            alt={product.name}
                            style={{ width: "100px", height: "100px", objectFit: "contain" }}
                        />
                    </div>
                )}
            </h1>
            <Card.Body>
                <Card.Title>{product.name}</Card.Title>
                <Card.Text>{product.description}</Card.Text>
                <Card.Text className="text-muted">
                    <span>R$ {product.price}/kg</span> <br />
                    <span>In Stock: {product.quantityInStock}</span>
                </Card.Text>
            </Card.Body>
        </div>
        </Card>
    );
};

export default ProductItem;
