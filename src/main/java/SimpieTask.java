class SimpleTask extends Task {
    protected String title;

    public SimpleTask(int id, String title) {
        super(id);
        this.title = title;
    }

    @Override
    public boolean matches(String query) {
        return title.contains(query);
    }

    public String getTitle() {
        return title;
    }
}