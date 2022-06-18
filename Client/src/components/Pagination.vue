<template>
  <div class="align-bottom">
    <div class="bg-gray-800 px-6 items-center justify-between rounded-xl">
      <div class="flex-1 flex justify-between sm:hidden content-end">
        <a
          href="#"
          class="
            relative
            inline-flex
            items-center
            px-4
            py-2
            border border-gray-300
            text-sm
            font-medium
            rounded-md
            text-gray-700
            bg-white
            hover:bg-gray-50
          "
        >
          Previous
        </a>
        <a
          href="#"
          class="
            ml-3
            relative
            inline-flex
            items-center
            px-4
            py-2
            border border-gray-300
            text-sm
            font-medium
            rounded-md
            text-gray-700
            bg-white
            hover:bg-gray-50
          "
        >
          Next
        </a>
      </div>
      <div class="hidden sm:flex-1 sm:flex sm:items-center sm:justify-between">
        <div>
          <p class="text-sm text-white">
            Showing
            <span class="font-medium">{{ getStartingPageNumber() }}</span>
            to
            <span class="font-medium">{{ getEndingPageNumber() }} </span>
            of
            <span class="font-medium">{{ totalElements }}</span>
            results
          </p>
        </div>
        <div>
          <nav
            class="
              relative
              z-0
              inline-flex
              rounded-md
              shadow-sm
              -space-x-px
              py-3
            "
            aria-label="Pagination"
          >
            <a
              v-if="this.getPreviousPage()"
              @click="this.$parent.getPage(getPreviousPage())"
              class="
                relative
                inline-flex
                items-center
                px-2
                py-2
                rounded-l-md
                border border-gray-400
                bg-gray-300
                text-sm
                font-medium
                text-gray-500
                hover:bg-gray-50
              "
            >
              <span class="sr-only">Previous</span>
              <!-- Heroicon name: solid/chevron-left -->
              <svg
                class="h-5 w-5"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 20 20"
                fill="currentColor"
                aria-hidden="true"
              >
                <path
                  fill-rule="evenodd"
                  d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                  clip-rule="evenodd"
                />
              </svg>
            </a>
            <a
              v-else
              class="
                relative
                inline-flex
                items-center
                px-2
                py-2
                rounded-l-md
                border border-gray-400
                bg-gray-300
                text-sm
                font-medium
                text-gray-500
                hover:bg-gray-50
              "
            >
              <span class="sr-only">Previous</span>
              <!-- Heroicon name: solid/chevron-left -->
              <svg
                class="h-5 w-5"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 20 20"
                fill="currentColor"
                aria-hidden="true"
              >
                <path
                  fill-rule="evenodd"
                  d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                  clip-rule="evenodd"
                />
              </svg>
            </a>
            <!-- Generowanie Paginacji -->
            <a
              v-for="page in this.getPagination()"
              :key="page"
              @click="this.$parent.getPage(page)"
              v-bind:class="
                page == this.index
                  ? 'z-10 bg-gray-300 border-gray-400 text-indigo-600 relative inline-flex items-center px-4 py-2 border text-sm font-medium'
                  : 'fa-checkbox-marked bg-gray-300 border-gray-400 text-gray-500 hover:bg-gray-50 relative inline-flex items-center px-4 py-2 border text-sm font-medium'
              "
              aria-current="page"
            >
              {{ page }}
            </a>
            <a
              v-if="this.getNextPage()"
              @click="this.$parent.getPage(this.getNextPage())"
              class="
                relative
                inline-flex
                items-center
                px-2
                py-2
                rounded-r-md
                border border-gray-400
                bg-gray-300
                text-sm
                font-medium
                text-gray-500
                hover:bg-gray-50
              "
            >
              <span class="sr-only">Next</span>
              <!-- Heroicon name: solid/chevron-right -->
              <svg
                class="h-5 w-5"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 20 20"
                fill="currentColor"
                aria-hidden="true"
              >
                <path
                  fill-rule="evenodd"
                  d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                  clip-rule="evenodd"
                />
              </svg>
            </a>
            <a
              v-else
              class="
                relative
                inline-flex
                items-center
                px-2
                py-2
                rounded-r-md
                border border-gray-400
                bg-gray-300
                text-sm
                font-medium
                text-gray-500
                hover:bg-gray-50
              "
            >
              <span class="sr-only">Next</span>
              <!-- Heroicon name: solid/chevron-right -->
              <svg
                class="h-5 w-5"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 20 20"
                fill="currentColor"
                aria-hidden="true"
              >
                <path
                  fill-rule="evenodd"
                  d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                  clip-rule="evenodd"
                />
              </svg>
            </a>
          </nav>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "PaginationComponent",
  methods: {
    getStartingPageNumber() {
      return (this.index - 1) * this.size + 1;
    },
    getEndingPageNumber() {
      var size = (this.index - 1) * this.size + this.size;
      if (size > this.totalElements) {
        return this.totalElements;
      }
      return size;
    },
    getPagination() {
      let pagination = new Set<number>();
      pagination.add(1);
      if (this.totalElements > 10) {
      pagination.add(this.totalPages);
      pagination.add(this.index);
        if (this.totalPages > 3) {
          let max = this.totalPages - 3 <= 6 ? this.totalPages - 3 : 6;
          if (this.index == 1 || this.index == this.totalPages) max++;

          let curr = 1;
          while (max > 0) {
            if (this.index - curr > 1) {
              pagination.add(this.index - curr);
              max--;
            }
            if (max == 0) {
              break;
            }
            if (this.index + curr < this.totalPages) {
              pagination.add(this.index + curr);
              max--;
            }
            curr++;
          }
        }
      }
      return Array.from(pagination).sort((a, b) => a - b);
    },
    getPreviousPage() {
      if (this.index > 1) {
        return this.index - 1;
      }
      return false;
    },
    getNextPage() {
      if (this.index < this.totalPages) {
        return this.index + 1;
      }
      return false;
    },
  },
  props: ["index", "size", "totalElements", "totalPages"],
});
</script>