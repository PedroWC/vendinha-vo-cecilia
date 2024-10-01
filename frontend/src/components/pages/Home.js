import React, {Fragment, useEffect, useState} from "react";
import Navbar from "../layout/Navbar";
import Sidebar from "../layout/Sidebar";
import Products from "../products/Products";
import { getAllProducts } from '../../services/productService';

const Home = () => {
    const [products, setProducts] = useState([]);
    const [searchText, setSearchText] = useState("");
    const [view, setView] = useState("column");

    // Função para buscar produtos
    const fetchProducts = async () => {
        try {
            const productsData = await getAllProducts();
            setProducts(productsData);
        } catch (err) {
            console.error("Erro ao carregar produtos:", err);
        }
    };

    useEffect(() => {
        fetchProducts().then(() => {});
    }, []);

    // Função de pesquisa
    const handleSearchTextChange = (text) => {
        setSearchText(text);
    };

    // Função para alterar visualização
    const handleViewChange = (viewType) => {
        setView(viewType);
    };

    // Filtra os produtos de acordo com o texto da pesquisa
    const filteredProducts = products.filter((product) =>
        product.name.toLowerCase().includes(searchText.toLowerCase())
    );

    return (
        <Fragment>
            <Navbar />
            <div className="container-fluid">
                <div className="row">
                    <div className="col-md-3 col-sm-12">
                        <div className="box">
                            <Sidebar
                                totalItems={products.length}
                                onSearchTextChange={handleSearchTextChange}
                                onViewChange={handleViewChange}
                            />
                        </div>
                    </div>
                    <div className="col-md-9 col-sm-12">
                        <Products
                            products={filteredProducts}
                            setProducts={setProducts}
                            view={view}
                        />
                    </div>
                </div>
            </div>
        </Fragment>
    );
};

export default Home;
