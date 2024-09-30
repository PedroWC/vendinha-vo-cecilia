import React from "react";
import { Link } from "react-router-dom";
import {deleteProduct} from "../../services/productService";

const ProductItem = ({ product, onDelete }) => {
    // Função para deletar o produto via API
    const handleDeleteProduct  = async (id) => {
        try {
            await deleteProduct(id);
            onDelete(id);
        } catch (error) {
            console.error("Erro ao deletar o produto:", error);
        }
    };

    return (
        <div className="box box__custom">
            <Link
                to={`/editProduct/${product.id}`}
                className="button button-edit is-warning"
            >
                Edit
            </Link>
            <button
                onClick={() => handleDeleteProduct(product.id)}
                className="button button-delete is-danger"
            >
                Delete
            </button>
            <h1 className="title is-1">
                <img src={product.picture} alt={product.name} width="100" />  {/* Exibe a imagem do produto */}
            </h1>
            <h2 className="subtitle is-5">{product.name}</h2>
            <p>{product.description}</p>  {/* Exibe a descrição do produto */}
            <span className="tag">₹ {product.price}/kg</span>
            <span className="tag">In Stock: {product.quantityInStock}</span>  {/* Exibe a quantidade em estoque */}
        </div>
    );
};

export default ProductItem;
