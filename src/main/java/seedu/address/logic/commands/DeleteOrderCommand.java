package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

public class DeleteOrderCommand extends Command{

    public static final String COMMAND_WORD = "deleteOrder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete an order from the address book,"
            + " order is always in lowercase. "
            + "\nParameters: [NAME OF ORDER]"
            + "\nExample: " + COMMAND_WORD + " cake";

    public static final String MESSAGE_SUCCESS = "Order deleted: %1$s";
    public static final String MESSAGE_ABSENT_ORDER = "This order does not exists in the address book";

    private final Order toDelete;

    public DeleteOrderCommand(String name) {
        this.toDelete = new Order(name.trim().toLowerCase());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasOrder(toDelete)) {
            model.removeOrder(toDelete);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
        }

        throw new CommandException(MESSAGE_ABSENT_ORDER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteOrderCommand)) {
            return false;
        }

        DeleteOrderCommand e = (DeleteOrderCommand) other;
        return this.toDelete.equals(e.toDelete);
    }
}
