<template>
  <div class="submitform">
    <div v-if="!submitted">
        <div class="form-group">
          <label for="name">Name</label>
          <input type="text" class="form-control" id="name" required v-model="book.name" name="name">
        </div>

        <div class="form-group">
            <label for="name">Author</label>
            <input type="text" class="form-control" id="author" required v-model="book.author" name="author">
        </div>
    
        <div class="form-group">
          <label for="releaseDate">Year</label>
          <input type="date" class="form-control" id="releaseDate" required v-model="book.releaseDate" name="releaseDate">
        </div>
    
        <button v-on:click="saveBook" class="btn btn-success">Submit</button>
    </div>
    
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" v-on:click="newBook">Add</button>
    </div>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "add-book",
    data: function () {
        return {
            book: {
                id: 0,
                name: "",
                author: "",
                releaseDate: 0,
                active: false
            },
            submitted: false
        };
    },
  methods: {
      /* eslint-disable no-console */
      saveBook: function () {
          var data = {
              name: this.book.name,
              author: this.book.author,
              releaseDate: this.book.releaseDate
          };

          http
              .post("/book", data)
              .then(response => {
                  this.book.id = response.data.id;
                  console.log(response.data);
              })
              .catch(e => {
                  console.log(e);
              });

          this.submitted = true;
      },
    newBook() {
      this.submitted = false;
      this.book = {};
    }
    /* eslint-enable no-console */
  }
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
