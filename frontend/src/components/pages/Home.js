import React, { Fragment, useState } from "react";
import Navbar from "../layout/Navbar";
import Sidebar from "../layout/Sidebar";
import Products from "../products/Products";

const Home = () => {
    const [view, setView] = useState("column"); // Estado para alternar entre visualizações de coluna e linha

    const handleViewChange = (viewType) => {
        setView(viewType); // Atualiza o estado de visualização
    };

    return (
        <Fragment>
            <Navbar />
            <div className="container-fluid">
                <div className="row">
                    {/* Sidebar ocupa 3 colunas e os produtos 9 colunas */}
                    <div className="col-md-3 col-sm-12">
                        <div className="box">
                            <Sidebar onViewChange={handleViewChange} totalItems={0} />
                        </div>
                    </div>
                    <div className="col-md-9 col-sm-12">
                        <Products view={view} />
                    </div>
                </div>
            </div>
        </Fragment>
    );
};

export default Home;
