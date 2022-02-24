import React from "react";
import { Table, ButtonGroup, Button, Form } from "react-bootstrap";
import AppAxios from "../../apis/AppAxios";

class Igraci extends React.Component {
  constructor(props) {
    super(props);

    const search = {
      klubId: -1,
      pozicija: "",
    };

    this.state = {
      igraci: [],
      klubovi: [],
      pageNo: 0,
      totalPages: 0,
      search: search,
    };
  }

  componentDidMount() {
    this.getIgraci(0);
    this.getKlubovi();
  }

  getIgraci(pageNo) {
    let config = {
      params: {
        pageNo: pageNo,
      },
    };

    if (this.state.search.pozicija != "") {
      config.params["pozicija"] = this.state.search.pozicija;
    }
    if (this.state.search.klubId != -1) {
      config.params["klubId"] = this.state.search.klubId;
    }

    AppAxios.get("/igraci", config)
      .then((res) => {
        console.log(res);
        this.setState({
          igraci: res.data,
          pageNo: pageNo,
          totalPages: res.headers["total-pages"],
        });
      })
      .catch((err) => {
        console.log(err);
        alert("Error occured please try again!");
      });
  }

  getKlubovi() {
    AppAxios.get("/klubovi")
      .then((res) => {
        console.log(res);
        this.setState({ klubovi: res.data });
      })
      .catch((err) => {
        console.log(err);
        alert("Error 'get klubovi'");
      });
  }

  delete(id) {
    AppAxios.delete("/igraci/" + id)
      .then((res) => {
        console.log(res);
        alert("Igrac je uspesno obrisan.");
        window.location.reload();
      })
      .catch((err) => {
        console.log(err);
        alert("Something wrong with delete...");
      });
  }

  transfer(id) {
      this.props.history.push('/igraci/transfer/' + id);
  }

  renderIgraci() {
    return this.state.igraci.map((igrac) => {
      return (
        <tr key={igrac.id} value={igrac.id}>
          <td>{igrac.imeIprezime}</td>
          <td>{igrac.pozicija}</td>
          <td>{igrac.brojDresa}</td>
          <td>{igrac.rodjenje}</td>
          <td>{igrac.prodaja ? "DA" : "NE"}</td>
          <td>{igrac.klubDTO.naziv}</td>
          {window.localStorage["role"] == "ROLE_ADMIN" ? (
          <td>
              <Button disabled={igrac.prodaja == false} onClick={() => this.transfer(igrac.id)}>Transfer</Button>
          </td>
          ) : null}
            {window.localStorage["role"] == "ROLE_ADMIN" ? (
          <td>
            <Button variant="danger" onClick={() => this.delete(igrac.id)}>
              Obrisi
            </Button>
          </td>
            ): null}
        </tr>
      );
    });
  }

  goToAdd() {
    this.props.history.push("/igraci/create");
  }

  changePage(direction) {
    const pageNo = this.state.pageNo + direction;

    this.getIgraci(pageNo);
  }

  submitSearch(e) {
    const name = e.target.name;
    const value = e.target.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({ search });
    this.getIgraci(0);
  }

  renderKlubovi() {
    return this.state.klubovi.map((klub) => {
        return (
          <option key={klub.id} value={klub.id}>
            {klub.naziv}
          </option>
        );
      });
}

  render() {
    return (
      <div>
        <Form>
          <Form.Group>
            <Form.Label>Klub</Form.Label>
            <Form.Control
              value={this.state.search.klubId}
              as="select"
              name="klubId"
              onChange={(e) => this.submitSearch(e)}
            >
              <option value={-1}></option>
              {this.renderKlubovi()}
            </Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Pozicija</Form.Label>
            <Form.Control
              value={this.state.search.pozicija}
              as="select"
              name="pozicija"
              onChange={(e) => this.submitSearch(e)}
            >
                <option value={null}></option>
                <option value="Napadac">Napadac</option>
                <option value="Vezni">Vezni</option>
                <option value="Defanzivac">Defanzivni</option>
                <option value="Golman">Golman</option>
            </Form.Control>
          </Form.Group>
        </Form>

        {window.localStorage["role"] == "ROLE_ADMIN" ? (
        <Button variant="success" onClick={() => this.goToAdd()}>
          Dodaj igraca
        </Button>
        ): null}

        <ButtonGroup style={{ float: "right" }}>
          <Button
            variant="info"
            disabled={this.state.pageNo == 0}
            onClick={() => this.changePage(-1)}
          >
            Prethodna
          </Button>
          <Button
            variant="info"
            disabled={this.state.totalPages == this.state.pageNo + 1}
            onClick={() => this.changePage(1)}
          >
            Sledeca
          </Button>
        </ButtonGroup>

        <Table
          style={{ marginTop: 5 }}
          striped
          bordered
          hover
          size="sm"
          id="igraci-table"
        >
          <thead className="thead-dark">
            <tr>
              <th>Ime i prezime</th>
              <th>Pozicija</th>
              <th>Broj dresa</th>
              <th>Datum rodjenja</th>
              <th>Na prodaju</th>
              <th>Naziv kluba</th>
              {window.localStorage["role"] == "ROLE_ADMIN" ? (
                <th></th>
              ) : null}
              {window.localStorage["role"] == "ROLE_ADMIN" ? <th></th> : null}
            </tr>
          </thead>
          <tbody>{this.renderIgraci()}</tbody>
        </Table>
      </div>
    );
  }
}

export default Igraci;
