import Vue from "vue";
import Router from "vue-router";
import AddBook from "./components/AddBook.vue";
import SearchBooks from "./components/SearchBooks.vue";
import BookList from "./components/BookList";
import Book from "./components/Book";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "books",
      alias: "/book",
      component: BookList,
      children: [
        {
          path: "/book/:id",
          name: "book-details",
          component: Book,
          props: true
        }
      ]
    },
    {
      path: "/add",
      name: "add",
      component: AddBook
    },
    {
      path: "/search",
      name: "search",
      component: SearchBooks
    }
  ]
});