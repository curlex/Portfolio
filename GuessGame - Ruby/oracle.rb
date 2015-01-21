class Oracle

  attr_reader :secret_number
  def initialize range
    @secret_number = rand(range.begin..range.end)
  end
  def is_it guessed_number
    @secret_number <=> guessed_number
  end


end
