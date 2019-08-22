<template>
  <div v-if="this.book">
    <h4>Customer</h4>
    <div>
      <label>Name: </label> {{this.book.name}}
    </div>
    <div>
      <label>Author: </label> {{this.book.author}}
    </div>
    <div>
      <label>Release date: </label> {{this.book.releaseDate}}
    </div>
    <div>
      <label>Active: </label> {{this.book.active}}
    </div>
  
    <span v-if="this.book.active"
      v-on:click="updateActive(false)"
      class="button is-small btn-primary">Inactive</span>
    <span v-else
      v-on:click="updateActive(true)"
      class="button is-small btn-primary">Active</span>
  
    <span class="button is-small btn-danger" v-on:click="deleteBook()">Delete</span>
  </div>
  <div v-else>
    <br/>
    <p>Please click on a Book...</p>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "book",
  props: ["book"],
  methods: {
    /* eslint-disable no-console */
    updateActive(status) {
      var data = {
        id: this.book.id,
        name: this.book.name,
        author: this.book.author,
        releaseDate: this.book.releaseDate,
        active: status
      };
      http
        .put("/book/" + this.book.id, data)
        .then(response => {
          this.book.active = response.data.active;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteBook() {
      http
        .delete("/book/" + this.book.id)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          this.$router.push('/');
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>