<script lang="ts">
import { Vue } from "vue-class-component";
import ClientsServices, { IClients } from "../../services/ClientsServices";

let temp_client: IClients = {
    id: 1,
    first_name: "Jan",
    last_name: "Nowak",
    email: "jkowalski2137@gmail.com",
    phone_number: "+48 667 444 321",
  };
export default class ClientsFetchView extends Vue {
  data() {
    return {
      result: temp_client,
    };
  }
}
</script>
