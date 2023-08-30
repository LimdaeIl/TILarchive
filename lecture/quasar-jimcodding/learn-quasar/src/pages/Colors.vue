<template>
  <q-page class="q-pa-xl">
    <section class="q-mb-xl">
      <div class="text-h4">Brand Colors</div>
      <q-separator class="q-my-md" />
      <div class="q-gutter-sm">
        <div class="q-pa-xs rounded-boarders bg-primary text-yellow">
          primary
        </div>
        <div class="q-pa-xs rounded-boarders bg-secondary text-white">
          secondary
        </div>
        <div class="q-pa-xs rounded-boarders bg-accent">accent</div>
        <div class="q-pa-xs rounded-boarders bg-dark">dark</div>
        <div class="q-pa-xs rounded-boarders bg-positive">positive</div>
        <div class="q-pa-xs rounded-boarders bg-negative">negative</div>
        <div class="q-pa-xs rounded-boarders bg-info">info</div>
        <div class="q-pa-xs rounded-boarders bg-warning">warning</div>
        <div class="q-pa-xs rounded-boarders bg-orange-4">warning</div>
        <div class="q-pa-xs rounded-boarders bg-light-green-6">warning</div>
        <div class="q-pa-xs rounded-boarders bg-blue-grey-1">warning</div>
        <p>Variables</p>
      </div>
    </section>
    <section class="q-mb-xl">
      <div class="text-h4">Components</div>
      <q-separator class="q-my-md" />
      <div class="q-pa-md q-gutter-sm">
        <q-btn color="white" text-color="black" label="Standard" />
        <q-btn color="primary" label="Primary" />
        <q-btn color="secondary" label="Secondary" />
        <q-btn color="amber" glossy label="Amber" />
        <q-btn color="brown-5" label="Brown 5" />
        <q-btn color="deep-orange" glossy label="Deep Orange" />
        <q-btn color="purple" label="Purple" />
        <q-btn color="black" label="Black" />
      </div>
    </section>
  </q-page>
</template>

<script setup></script>

<style lang="scss" scoped>
p {
  color: $red;
  background-color: $grey-5;
}

.text-error {
  color: $red;
}

.bg-error {
  background: $red;
}
</style>
