<template>
  <div class="searchform">
    <h4>Find by name</h4>
    <div class="form-group">
      <input type="text" class="form-control" id="name" required v-model="name" name="name">
    </div>
 
    <div class="btn-group">
      <button v-on:click="searchBooks" class="btn btn-success">Search</button>
    </div>

    <ul class="search-result">
      <li v-for="(book, index) in books" :key="index">
        <h6>{{book.name}} ({{book.releaseDate}})</h6>
      </li>
    </ul>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "search-book",
  data() {
    return {
      name: "",
      books: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    searchBooks() {
      http
        .get("/books/name/" + this.name)
        .then(response => {
          this.books = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>

<style>
.searchform {
  max-width: 300px;
  margin: auto;
}
.search-result {
  margin-top: 20px;
  text-align: left;
}
</style>