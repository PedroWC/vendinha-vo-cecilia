import React from "react";

const ColumnView = (props) => {
    return <div className="col-md-4 col-sm-12 text-center mb-4">{props.children}</div>;  // Ajuste para Bootstrap
};

const RowView = (props) => {
    return <div className="col-12 text-center mb-4">{props.children}</div>;  // Ajuste para Bootstrap
};

const View = ({ view, children }) => {
    if (view === "column") return <ColumnView>{children}</ColumnView>;
    return <RowView>{children}</RowView>;
};

export default View;
