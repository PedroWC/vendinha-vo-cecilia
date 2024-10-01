import React, { useState } from "react";
import '@fortawesome/fontawesome-free/css/all.min.css';

const Sidebar = ({ totalItems, onSearchTextChange, onViewChange }) => {
    const [view, setView] = useState("column"); // Estado local para o tipo de exibição
    const [searchText, setSearchText] = useState(""); // Estado local para o texto de busca

    // Função para alterar a visualização (coluna ou linha)
    const handleViewChange = (viewType) => {
        setView(viewType);
        if (onViewChange) {
            onViewChange(viewType); // Notifica o componente pai da mudança de exibição
        }
    };

    // Função para alterar o texto de busca
    const handleSearchTextChange = (e) => {
        setSearchText(e.target.value);
        onSearchTextChange(e.target.value); // Notifica o componente pai
    };

    return (
        <div className="sidebar">
            <h2 className="subtitle">
                ({totalItems}) products
            </h2>
            <div className="btn-group">
                <button
                    className={`btn ${view === "column" ? "btn-dark" : "btn-light"}`}
                    onClick={() => handleViewChange("column")}
                >
                    <i className="fas fa-th-large"></i>
                </button>
                <button
                    className={`btn ${view === "row" ? "btn-dark" : "btn-light"}`}
                    onClick={() => handleViewChange("row")}
                >
                    <i className="fas fa-grip-horizontal"></i>
                </button>
            </div>
            <h3>Search Products</h3>
            <input
                className="form-control"
                type="text"
                placeholder="Search your product..."
                value={searchText}
                onChange={handleSearchTextChange}
            />
        </div>
    );
};

export default Sidebar;
