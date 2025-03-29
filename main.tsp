import "@typespec/http";

using TypeSpec.Http;
@service(#{ title: "Todo Application For Otel" })
namespace DemoService;

model Todo {
  id: string;
  title: string;
  description?: string;
  expire?: offsetDateTime;
  completed?: boolean;
}

model TodoList {
  items: Todo[];
}

@error
model Error {
  code: int32;
  message: string;
}

@route("/todos")
@tag("Todos")
interface Todos {
  /** List todo */
  @get list(): TodoList | Error;
  /** Read todo */
  @get read(@path id: string): Todo | Error;
  /** Create a todo */
  @post create(@body body: Todo): Todo | Error;
  /** Update a todo */
  @patch update(@path id: string, @body body: Todo): Todo | Error;
  /** Delete a todo */
  @delete delete(@path id: string): void | Error;
}
