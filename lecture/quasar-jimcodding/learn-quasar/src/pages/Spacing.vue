<template>
  <q-page class="q-pa-xl">
    <section class="q-mb-xl">
      <div class="text-h4">Sapcing</div>
      <q-separator class="q-my-md" />
      <div class="bg-primary q-pa-xs q-mt-md">
        <div class="bg-dark text-white">
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Inventore,
          in? Repellat officiis voluptate nam repudiandae placeat, pariatur at
          consectetur deleniti quibusdam ullam provident omnis voluptatibus
          neque aliquid, veritatis exercitationem in.
        </div>
      </div>
      <div class="bg-primary q-pa-sm q-mt-md">
        <div class="bg-dark text-white">
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Inventore,
          in? Repellat officiis voluptate nam repudiandae placeat, pariatur at
          consectetur deleniti quibusdam ullam provident omnis voluptatibus
          neque aliquid, veritatis exercitationem in.
        </div>
      </div>
      <div class="bg-primary q-pa-md q-mt-md">
        <div class="bg-dark text-white">
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Inventore,
          in? Repellat officiis voluptate nam repudiandae placeat, pariatur at
          consectetur deleniti quibusdam ullam provident omnis voluptatibus
          neque aliquid, veritatis exercitationem in.
        </div>
      </div>
      <div class="bg-primary q-pa-lg q-mt-md">
        <div class="bg-dark text-white">
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Inventore,
          in? Repellat officiis voluptate nam repudiandae placeat, pariatur at
          consectetur deleniti quibusdam ullam provident omnis voluptatibus
          neque aliquid, veritatis exercitationem in.
        </div>
      </div>
      <div class="bg-primary q-pa-xl q-mt-md">
        <div class="bg-dark text-white">
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Inventore,
          in? Repellat officiis voluptate nam repudiandae placeat, pariatur at
          consectetur deleniti quibusdam ullam provident omnis voluptatibus
          neque aliquid, veritatis exercitationem in.
        </div>
      </div>
      <div
        class="bg-primary q-mt-md flex flex-center"
        style="width: 100%; height: 400px"
      >
        <div class="bg-dark text-white" style="width: 100px; height: 100px">
          Lorem
        </div>
      </div>
    </section>
    <section class="q-mb-xl">
      <div class="text-h4">Components</div>
      <q-separator class="q-my-md" />
      <q-card
        class="my-card bg-secondary text-white q-mx-auto"
        style="width: 400px"
      >
        <q-card-section>
          <div class="text-h6">Our Changing Planet</div>
          <div class="text-subtitle2">by John Doe</div>
        </q-card-section>

        <q-card-section>
          {{ lorem }}
        </q-card-section>

        <q-separator dark />

        <q-card-actions>
          <q-btn padding="lg">Action 1</q-btn>
          <q-btn padding="none">Action 2</q-btn>
        </q-card-actions>
      </q-card>
    </section>
  </q-page>
</template>

<script setup></script>

<style lang="scss" scoped></style>
