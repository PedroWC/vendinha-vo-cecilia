import React from "react";

const ColumnView = (props) => {
  return <div className="column is-4 has-text-centered">{props.children}</div>;
};

const RowView = (props) => {
  return <div className="column is-12 has-text-centered">{props.children}</div>;
};

const View = ({ view, children }) => {
  if (view === "column") return <ColumnView>{children}</ColumnView>;
  return <RowView>{children}</RowView>;
};

export default View;
