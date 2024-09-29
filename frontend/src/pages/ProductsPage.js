import { useEffect, useState } from 'react';
import axios from 'axios';

function ProductsPage() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await axios.get('/api/product');
                setProducts(response.data);
            } catch (error) {
                console.error('Erro ao buscar produtos', error);
            }
        };

        fetchProducts();
    }, []);

    return (
        <div>
            <h1>Lista de Produtos</h1>
            <ul>
                {products.map((product) => (
                    <li key={product.id}>{product.name} - {product.price}</li>
                ))}
            </ul>
        </div>
    );
}

export default ProductsPage;
