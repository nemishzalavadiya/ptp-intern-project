import { useState, useEffect } from "react";
import Head from "next/head";
import Link from "next/link";
import FilterGroup from "src/components/filter/FilterGroup";
import Layout from "src/components/Layout";
import { mutualFundFilters } from "src/components/filter/filterDetails";
import { filterType } from "src/components/filter/filterType.tsx";
import GridContainer from "src/components/grid/GridContainer";
import { useRouter } from "next/router";
import Sorting from "src/components/Sorting/Sorting";
import {MutualFundSortingField} from "src/components/Sorting/sortingField";
import { Card, Grid, Icon, Loader } from "semantic-ui-react";

const mutualfunds = () => {
  const router = useRouter();
  const content = [
    { header: "Company_Id", icon: "" },
    { header: "Risk", icon: "" },
    { header: "Min Sip", icon: <i className="rupee sign icon small"></i> },
    { header: "Fund Size", icon: <i className="rupee sign icon small"></i> },
  ];
  const [pattern, setPattern] = useState([0, 0, 0,0]);
  const [orderBy, setOrderBy] = useState("");
  const [sortingField, setSortingField] = useState("");
  function changeArrow(index, fieldName) {
    let d = [];
<<<<<<< HEAD
    let size = content.length;
=======
    let size = MutualFundSortingField.length;
>>>>>>> 5f80132... sorting style
    for (let i = 0; i < size; i++) {
      d.push(0);
    }
    d[index] = 1 - pattern[index];
    setPattern(d);
    if (d[index] == 1) {
      setOrderBy("DESC");
    } else {
      setOrderBy("ASC");
    }
    setSortingField(fieldName);
    console.log(fieldName);
  }
  const initialState = {
    results: [],
    selectedFilters: Array(
      ...mutualFundFilters.map((filter) =>
        filter.type == filterType.RANGE
          ? { minimum: filter.minimum, maximum: filter.maximum }
          : []
      )
    ),
  };

  const [results, setResults] = useState(initialState.results);
  const [selectedFilters, setSelectedFilters] = useState(
    initialState.selectedFilters
  );

  //method to get index by field from mutualFundFilter
  function getIndexFromMutualFundFilterByField(field) {
    for (let [index, filter] of mutualFundFilters.entries()) {
      if (filter.field === field) {
        return index;
      }
    }
    return null;
  }

  //adding dashboard filters
  if (Object.keys(router.query).length !== 0) {
    for (let filters in router.query) {
      let filtersData = JSON.parse(router.query[filters].toString());
      for (let field in filtersData) {
        let index = getIndexFromMutualFundFilterByField(field.toString());
        if (index != null) {
          if (mutualFundFilters[index].type === filterType.CHECKBOX) {
            initialState.selectedFilters[index].push(filtersData[field]);
          } else if (mutualFundFilters[index].type === filterType.RANGE) {
            for (let key in filtersData[field]) {
              initialState.selectedFilters[index][key] =
                filtersData[field][key];
            }
          }
        }
      }
    }
    router.replace("/mutualfunds", undefined, { shallow: true });
  }
  async function requestFiltered(url = "", data = {}) {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    return response.json();
  }

  const [activePage, setActivePage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  async function requestFiltered(url = "", data = {}) {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    console.log(data, url);

    return response.json();
  }

  useEffect(() => {
    let filterBody = {};
    selectedFilters.forEach((filter, index) => {
      filterBody[mutualFundFilters[index].field] = filter;
    });
    requestFiltered(
      `/api/mutualfunds/filters?page=${activePage}&sortingField=${sortingField}&orderBy=${orderBy}`,
      filterBody
    ).then((page) => {
      console.log("Called this one");
      setResults(page.content);
      console.log(page.content);
      setTotalPages(page.totalPages);
    });
    console.log("clicked ", orderBy);
  }, [orderBy, activePage, selectedFilters, sortingField]);

  const pageReset = () => {
    setActivePage(0);
  };

  const setSelectedState = (selectedGroupState) => {
    setSelectedFilters(selectedGroupState);
  };

  return (
    <Layout name="MUTUAL_FUND">
      <Head>
        <title>Pirimid Trading Platform</title>
        <link rel="icon" href="/favicon.svg" />
      </Head>
      <div className="filter-grid">
        <FilterGroup
          details={mutualFundFilters}
          pageReset={pageReset}
          selectedFilters={selectedFilters}
          setSelectedState={setSelectedState}
        />
        <div className="right-grid">
          <Sorting content={content} pattern={pattern} onclick={changeArrow} />
          <GridContainer
            content={content}
            data={
              results === undefined
                ? []
                : results.map((item) => [
                    <Link
                      href={`/details/${item.mutualFundDetail.assetDetail.id}`}
                    >
                      {item.mutualFundDetail.assetDetail.name}
                    </Link>,
                    item.risk,
                    item.minSIP,
                    item.fundSize,
                  ])
            }
            pagination={{
              activePage,
              totalPages,
              handlePaginationChange: setActivePage,
            }}
          />
        </div>
      </div>
    </Layout>
  );
};

export default mutualfunds;
