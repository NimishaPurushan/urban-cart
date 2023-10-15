import {render, screen} from '@testing-library/react'
import { describe, expect, it } from "vitest";
import App from "./App";
import "@testing-library/jest-dom";

describe("<App />", () => {
  it("App", () => {
    render(<App />);
    expect(document.title).toBe("Urban Cart");
    screen.debug();
  });
});
