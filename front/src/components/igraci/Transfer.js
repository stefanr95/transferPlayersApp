import React from "react";
import { Button, Form } from "react-bootstrap";
import AppAxios from "../../apis/AppAxios";

class Transfer extends React.Component {
  constructor(props) {
    super(props);

    const transfer = {
      cena: 0,
      klubDTO: null,
    };

    this.state = { transfer: transfer, igrac: {}, klubovi: [] };
  }

  componentDidMount() {
    this.getIgraciById(this.props.match.params.id);
    this.getKlubovi();
  }

  getIgraciById(id) {
    AppAxios.get("/igraci/" + id)
      .then((res) => {
        console.log(res);
        this.setState({ igrac: res.data });
      })
      .catch((err) => {
        console.log(err);
        alert("Error 'get id'");
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

  renderKlubovi() {
    return this.state.klubovi.map((klub) => {
        return (
          <option key={klub.id} value={klub.id}>
            {klub.naziv}
          </option>
        );
      });
  }

  transfer() {
      let transferDTO = {
        cena: this.state.transfer.cena,
        klubDTO: this.state.transfer.klubDTO,
        igracDTO: this.state.igrac
      }

      AppAxios.post("/transferi", transferDTO)
        .then((res) => {
          console.log(res);
          alert("Transfer je uspesno uradjen.");
          this.props.history.push("/igraci");
        })
        .catch((err) => {
          console.log(err);
          alert("Neuspesan transfer! Klub nema dovoljno novca.");
        });
  }

  inputChange(e) {
    const value = e.target.value;
    const name = e.target.name;
    console.log(value);
    
    let transfer = this.state.transfer;
    transfer[name] = value;
    this.setState({ transfer: transfer });
  }

  selectionChanged(e) {
    let value = e.target.value;
    let klubDTO = this.state.klubovi.find(klubovi => klubovi.id == value);

    let transfer =  this.state.transfer;
    transfer.klubDTO = klubDTO;

    this.setState({transfer: transfer})
}

  render() {
    return (
      <div>
        <Form>
          <Form.Group>
            <Form.Label>Cena transfera</Form.Label>
            <Form.Control
              type="number"
              min="0"
              name="cena"
              placeholder="Cena transfera"
              onChange={(e) => this.inputChange(e)}
            ></Form.Control>
          </Form.Group>

          <Form.Group>
            <Form.Label>Klub</Form.Label>
            <Form.Control
              as="select"
              name="klubId"
              onChange={(e) => this.selectionChanged(e)}
            >
              <option></option>
              {this.renderKlubovi()}
            </Form.Control>
          </Form.Group>

          <Button onClick={() => this.transfer()}>Uradi transfer</Button>
        </Form>
      </div>
    );
  }
}

export default Transfer;
