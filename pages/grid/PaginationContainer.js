/*
  Component: PaginationContainer
  props: pagination: Object {
    activePage:number,totalPages*:number,
    handlePaginationChange(pageNumber): method
  }
*/
import { Grid, Pagination, Icon } from "semantic-ui-react";
import { useState } from "react";
export default function PaginationContainer(props) {

  const [state,setState] = useState({
    activePage: 0,
    boundaryRange: 2,
    siblingRange: 2,
    totalPages: props.pagination.totalPages,
  });

  function handlePaginationChange(e, { activePage }){
    setState({
      activePage: activePage,
      boundaryRange: 2,
      siblingRange: 2,
      totalPages: props.pagination.totalPages,
    })
    props.pagination.handlePaginationChange(activePage);
  }

  return (
    <Grid columns={1}>
      <Grid.Column>
        <Pagination
          activePage={state.activePage}
          boundaryRange={state.boundaryRange}
          onPageChange={handlePaginationChange}
          size="small"
          siblingRange={state.siblingRange}
          totalPages={state.totalPages}
          ellipsisItem={{
            content: <Icon name="ellipsis horizontal" />,
            icon: true,
          }}
          firstItem={{ content: <Icon name="angle double left" />, icon: true }}
          lastItem={{ content: <Icon name="angle double right" />, icon: true }}
          prevItem={{ content: <Icon name="angle left" />, icon: true }}
          nextItem={{ content: <Icon name="angle right" />, icon: true }}
        />
      </Grid.Column>
    </Grid>
  );
}
