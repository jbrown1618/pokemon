import { PokemonClientPage } from './app.po';

describe('pokemon-client App', () => {
  let page: PokemonClientPage;

  beforeEach(() => {
    page = new PokemonClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
