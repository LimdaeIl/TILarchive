<template>
  <q-layout view="lHh Lpr lFf" class="bg-white">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="toggleLeftDrawer"
          aria-label="Menu"
          icon="menu"
        />

        <q-toolbar-title> Quasar App </q-toolbar-title>

        <q-btn stretch flat label="Main Layout" to="/" />
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered class="bg-grey-2">
      <q-list>
        <q-item-label header>Essential Links</q-item-label>
        <q-item v-for="{ title, to } in items" :key="title" clickable :to="to">
          <q-item-section avatar>
            <q-icon name="school" />
          </q-item-section>
          <q-item-section>
            <q-item-label>{{ title }}</q-item-label>
            <q-item-label caption>https://quasar.dev</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref } from 'vue';

const items = ref([
  { title: 'Sub Page 1', to: '/sub/sub-page-1' },
  { title: 'Sub Page 2', to: '/sub/sub-page-2' },
]);

const leftDrawerOpen = ref(false);

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}
</script>
