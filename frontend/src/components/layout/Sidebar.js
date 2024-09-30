import React, { useState } from "react";

const Sidebar = ({ totalItems, onSearchTextChange, onViewChange }) => {
  const [view, setView] = useState("column"); // Estado local para o tipo de exibição
  const [searchText, setSearchText] = useState(""); // Estado local para o texto de busca

  // Função para alterar a visualização (coluna ou linha)
  const handleViewChange = (viewType) => {
    setView(viewType);
    onViewChange(viewType);  // Notifica o componente pai da mudança de exibição
  };

  // Função para alterar o texto de busca
  const handleSearchTextChange = (e) => {
    setSearchText(e.target.value);
    onSearchTextChange(e.target.value);  // Notifica o componente pai
  };

  return (
      <div className="columns is-multiline">
        <div className="column is-12">
          <h2 className="subtitle">({totalItems}) products</h2>
          <div className="field has-addons">
            <div className="control">
              <button
                  className={`button ${view === "column" ? `is-dark` : ""}`}
                  onClick={() => handleViewChange("column")}
              >
                <i className="fas fa-th-large"></i>
              </button>
            </div>
            <div className="control">
              <button
                  className={`button ${view === "row" ? `is-dark` : ""}`}
                  onClick={() => handleViewChange("row")}
              >
                <i className="fas fa-grip-horizontal"></i>
              </button>
            </div>
          </div>
          <h3 className="subtitle is-6 mb-2">Search Products</h3>
          <input
              className="input"
              type="text"
              placeholder="Search your product..."
              value={searchText}
              onChange={handleSearchTextChange}
          />
        </div>
      </div>
  );
};

export default Sidebar;
