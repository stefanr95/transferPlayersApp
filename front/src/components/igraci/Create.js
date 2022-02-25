import React from "react";
import { Row, Col, Form, Button } from "react-bootstrap";
import AppAxios from "../../apis/AppAxios";

class Create extends React.Component {
  constructor(props) {
    super(props);

    let igrac = {
      imeIprezime: "",
      pozicija: "",
      brojDresa: 0,
      rodjenje: "",
      prodaja: false,
      klubDTO: null,
    };

    this.state = { igrac: igrac, klubovi: [] };
  }

  componentDidMount() {
    this.getKlubovi();
  }

  getKlubovi() {
    AppAxios.get("/klubovi")
      .then((res) => {
        console.log(res);
        this.setState({ klubovi: res.data });
      })
      .catch((err) => {
        console.log(err);
        alert("Add error (clubs)!");
      });
  }

  create() {
    let igrac = this.state.igrac;
    let igracDTO = {
        imeIprezime: igrac.imeIprezime,
        pozicija: igrac.pozicija,
        brojDresa: igrac.brojDresa,
        rodjenje: igrac.rodjenje,
        prodaja: igrac.prodaja,
        klubDTO: igrac.klubDTO
    }

    AppAxios.post("/igraci", igracDTO)
        .then(res => {
            console.log(res)
            alert('Igrac je uspesno kreiran.')
            this.props.history.push("/igraci")
        })
        .catch(err => {
            console.log(err)
            alert("Uneti podaci nisu ispravni, probajte ponovo!")
        })
  }

  valueInputChanged(e) {
    const name = e.target.name;
    const value = e.target.value;

    let igrac = this.state.igrac;
    igrac[name] = value;
    
    this.setState({igrac: igrac});
  }

  selectionChanged(e) {
    let value = e.target.value;
    let klubDTO = this.state.klubovi.find(klubovi => klubovi.id == value);

    let igrac =  this.state.igrac;
    igrac.klubDTO = klubDTO;

    this.setState({igrac: igrac})
}

cheked(e) {

    const checked = e.target.checked;

    let igrac = this.state.igrac;

    (checked)? igrac.prodaja = true : igrac.prodaja = false;
    this.setState({igrac: igrac})
   
}

  render() {
    return (
      <div>
        <Row className="justify-content-center">
          <Col md={6}>
            <Form>
              <h1>Dodaj igraca</h1>
              <Form.Group>
                <Form.Label>Ime i prezime</Form.Label>
                <Form.Control
                  value={this.state.igrac.imeIPrezime}
                  name="imeIprezime"
                  placeholder="Ime i prezime"
                  onChange={(e) => this.valueInputChanged(e)}
                />
                <br />
              </Form.Group>

              <Form.Group>
                <Form.Label>Pozicija</Form.Label>
                <Form.Control
                  value={this.state.igrac.pozicija}
                  name="pozicija"
                  as="select"
                  onChange={(e) => this.valueInputChanged(e)}
                >
                <option value="">Pozicija</option>
                <option value="Napadac">Napadac</option>
                <option value="Vezni">Vezni</option>
                <option value="Defanzivac">Defanzivac</option>
                <option value="Golman">Golman</option>
                </Form.Control>
                <br />
              </Form.Group>

              <Form.Group>
                <Form.Label>Broj dresa</Form.Label>
                <Form.Control
                  value={this.state.igrac.brojDresa}
                  name="brojDresa"
                  placeholder="Broj dresa"
                  onChange={(e) => this.valueInputChanged(e)}
                />
                <br />
              </Form.Group>

              <Form.Group>
                <Form.Label>Datum rodjenja</Form.Label>
                <Form.Control
                  value={this.state.igrac.datumRodjenja}
                  name="rodjenje"
                  placeholder="yyyy-mm-dd"
                  onChange={(e) => this.valueInputChanged(e)}
                />
                <br />
              </Form.Group>

              <Form.Group>
                <Form.Check
                  value={this.state.igrac.naProdaju}
                  type="checkbox"
                  label="Na prodaju"
                  name="prodaja"
                  onClick={(e) => this.cheked(e)}
                />
                <br />
              </Form.Group>


              <Form.Group>
                <Form.Label>Klub</Form.Label>

                <Form.Control
                  as="select"
                  name="mestoDTO"
                  onChange={(event) => this.selectionChanged(event)}
                >
                  <option>Izaberi klub</option>
                  {this.state.klubovi.map((klub) => {
                    return (
                      <option key={klub.id} value={klub.id}>
                        {klub.naziv}
                      </option>
                    );
                  })}
                </Form.Control>
                <br />
              </Form.Group>

              <Button onClick={() => this.create()}> Kreiraj</Button>
            </Form>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Create;
